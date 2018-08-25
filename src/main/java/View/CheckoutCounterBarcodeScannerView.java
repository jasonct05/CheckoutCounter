package View;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.TransactionProcessingModel;
import Util.BufferedImageLuminanceSource;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import static Util.Utility.debug;

public class CheckoutCounterBarcodeScannerView extends JPanel implements Runnable, ThreadFactory {

    private static final long serialVersionUID = 6441489157408381878L;

    private Executor executor = Executors.newSingleThreadExecutor(this);

    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private TransactionProcessingModel tpm;

    public CheckoutCounterBarcodeScannerView(TransactionProcessingModel tpm, Dimension d) {
        super();

        this.tpm = tpm;
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);

        add(panel);

        executor.execute(this);
    }

    public void run() {

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall through, it means there is no QR code in image
                }
            }

            if (result != null && this.tpm.getTransaction() == null) {
                debug("received barcode with string: " + result.getText());
                this.tpm.queryTransaction(result.getText());
            }

            if (image != null && this.tpm.getTransaction() != null && this.tpm.getTransaction().getRequestImageURL() != null) {
                try {
                    // retrieve image
                    System.out.println("saving image");
                    File outputfile = new File("cache/" + this.tpm.getTransaction().getRequestImageURL() + ".png");
                    ImageIO.write(image, "png", outputfile);
                } catch (Exception e) {
                    // do nothing
                }
            }

        } while (true);
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }
}