package com.example.desafioimagem2.controller;

import com.example.desafioimagem2.service.ImagemService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;

@RestController
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @PostMapping("/comparar")
    public Object compararImagem(@RequestBody String nome) throws IOException, InterruptedException, BrokenBarrierException {
        final String nomePais = new Gson().fromJson(nome, HashMap.class).get("nomePais").toString();
        return imagemService.compararImagem(nomePais);
    }
}
