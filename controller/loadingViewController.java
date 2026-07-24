package controller;

import javafx.fxml.FXML;

public class loadingViewController {

    public void startLoading(Runnable onFinished) {
        if (onFinished != null) {
            onFinished.run();
        }
    }
}