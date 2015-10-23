
//package  controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ResourceBundle;
//
//import javax.swing.JPanel;
//
//import  view.usuario.UsuarioUI;

//public class UsuarioController extends UsuarioUI {
//
//  public UsuarioController(JPanel conteudo, ResourceBundle bundle) {
//    super(conteudo, bundle);
//
//    addConsultarListener(new ConsultarHandler());
//    addCadastrarListener(new CadastrarHandler());
//  }
//
//  private class ConsultarHandler implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//      new ConsultarUsuarioController(getConteudo(), bundle, atualizar, deletar);
//    }
//  }
//
//  private class CadastrarHandler implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//      new CriarUsuarioController(getConteudo(), bundle, atualizar, deletar);
//    }
//  }
//
// }