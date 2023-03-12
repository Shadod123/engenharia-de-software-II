package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.Contato;
import model.DbOperations;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public class ContatoService {
    public ContatoService() {}

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Contato contato = DbOperations.findRecordById(id);

		if(contato == null) {
            System.out.println("ERRO AAAAAAAAAAAAAAAAAAAAA");
		}

        return contato;
    }

    public Object add(Request request, Response response) {
        String nome = request.queryParams("nome");
        String endereco = request.queryParams("endereco");
        String telefone = request.queryParams("telefone");

		int id = DbOperations.createRecord(nome, endereco, telefone);

        response.status(201); // 201 Created
        return id;
    }

    public Object update(Request request, Response response) {
        return new String();
    }

    public Object remove(Request request, Response response) {
        return new String();
    }
}
