package com.gh.study.game_controller;

import static com.gh.study.container.Container.scanner;

public class ExitController {
    public void gameExit() {
        scanner.close();
        System.exit(0);
    }
}
