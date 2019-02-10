package Visualization.Implementation;

import Visualization.Contract.IImageExporter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PngImageExporter implements IImageExporter {

    private int _height;
    private int _width;

    private String _uuid;
    private int _exportNo;

    public PngImageExporter(int height, int width) {

        _height = height;
        _width = width;

        _uuid = UUID.randomUUID().toString();
        _exportNo = 0;
    }

    @Override
    public void exportImage(Canvas canvas) {
        WritableImage wim = new WritableImage(_width, _height);
        canvas.snapshot(null, wim);
        File file = new File(System.getProperty("user.home") + "/Desktop/bug-crawler-cap-" + _uuid + "-" + _exportNo + ".png");
        _exportNo++;

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
