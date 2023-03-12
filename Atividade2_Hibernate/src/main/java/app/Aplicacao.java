package app;

import static spark.Spark.*;

import service.ContatoService;

public class Aplicacao {
    private static ContatoService contatoService = new ContatoService();

    public static void main(String[] args) {
        staticFiles.location("/");

        port(6789);

        post("/contato", (request, response) -> contatoService.add(request, response));

        get("/contato/:id", (request, response) -> contatoService.get(request, response));

        get("/contato/update/:id", (request, response) -> contatoService.update(request, response));

        get("/contato/delete/:id", (request, response) -> contatoService.remove(request, response));

        get("/contato", (request, response) -> contatoService.getAll(request, response));
    }
}
