package dao;

import java.sql.SQLException;
import java.util.List;

import controller.UsuarioLoader;
import jdbc.SqlStatement;
import jdbc.SqlStatementWrapper;
import model.PessoaFisica;
import model.Usuario;
import util.CPF;
import util.RequestParam;

public class UsuarioDAO implements Usuario.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.USUARIO as USUARIO")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PESSOAFISICA.ID = USUARIO.PESSOAFISICA_ID")

        .load(new UsuarioLoader());
  }

  @Override
  public void criar(Usuario usuario) throws SQLException {
    PessoaFisica pessoaFisica = usuario.getPessoaFisica();
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.USUARIO")
        .with("(ID, PESSOAFISICA_ID, CODIGO, PERFIL, LOGIN, SENHA)")

        .with("values (")
        .with("?,", usuario.getId())
        .with("?,", pessoaFisica.getId())
        .with("?,", usuario.getCodigo())
        .with("?,", usuario.getPerfil().ordinal())
        .with("?,", usuario.getLogin())
        .with("?)", usuario.getSenha())

        .andExecute();
  }

  @Override
  public List<Usuario> consultar(RequestParam request) {
    String login = request.stringParam("login");
    String codigo = request.stringParam("codigo");

    return query()

        .with("where 1 = 1")
        .with("and USUARIO.LOGIN like concat ('%',?,'%')", login)
        .with("and USUARIO.CODIGO like concat ('%',?,'%')", codigo)
        .with("order by USUARIO.CODIGO asc")

        .andList();
  }

  @Override
  public Usuario consultarUsuario(RequestParam request, String cript) {
    String login = request.stringParam("login");
    String senha = cript;

    return query()

        .with("where 1 = 1")
        .with("and USUARIO.LOGIN = ?", login)
        .with("and USUARIO.SENHA = ?", senha)

        .andGet();
  }

  @Override
  public Usuario consultarPorCpf(CPF cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf.getDigito())

        .andGet();
  }

  @Override
  public Usuario consultarPorId(int id) {
    return query()

        .with("where USUARIO.ID = ?", id)

        .andGet();
  }

  @Override
  public boolean atualizar(Usuario usuario) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.USUARIO set")

        .with("PERFIL = ?,", usuario.getPerfil().ordinal())
        .with("LOGIN = ?,", usuario.getLogin())
        .with("SENHA = ?", usuario.getSenha())

        .with("where ID = ?", usuario.getId())

        .andExecute();

    return executed;
  }

  @Override
  public void deletar(int id) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.USUARIO")
        .with("where ID = ?", id)

        .andExecute();
  }

}