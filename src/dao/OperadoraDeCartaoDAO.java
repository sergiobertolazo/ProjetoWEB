package  dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.joda.time.LocalDate;

import  controller.CartaoCreate;
import  model.Bandeira;
import  model.Cartao;
import  util.ReaderFile;
import  util.RequestParamWrapper;


public class OperadoraDeCartaoDAO implements ReaderFile<Cartao> {

  private final List<Cartao> list;

  public OperadoraDeCartaoDAO() {
    list = new ArrayList<Cartao>();
  }

  private Scanner input;

  @Override
  public void openFile() {
    try {
      input = new Scanner(new File("operadora_cartao.txt"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Cartao> readFile() {
    try {
      while (input.hasNext()) {
        RequestParamWrapper request = new RequestParamWrapper();

        String titular = input.next();
        long numero = input.nextLong();
        String data = input.next();
        String cartao = input.next();
        double valor = 0;

        request.set("titular", titular);
        request.set("numero", numero);

        LocalDate validade = LocalDate.parse(data);
        request.set("validade", validade);

        Bandeira bandeira = Bandeira.valueOf(Bandeira.class, cartao);
        request.set("bandeira", bandeira);

        request.set("valor", valor);

        Cartao pojo = new CartaoCreate(request).createInstance();
        list.add(pojo);
      }
    } catch (NoSuchElementException e) {
      input.close();
    } catch (IllegalStateException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override
  public void closeFile() {
    if (input != null) {
      input.close();
    }
  }

}