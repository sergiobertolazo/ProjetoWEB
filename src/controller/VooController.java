
//package  controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ResourceBundle;
//
//import javax.swing.JPanel;
//
//import  model.Usuario;
//import  view.voo.VooUI;
//

//public class VooController extends VooUI {
//
//  public VooController(JPanel conteudo, ResourceBundle bundle, Usuario usuarioLogado) {
//    super(conteudo, bundle, usuarioLogado);
//
//    addConsultarListener(new ConsultarHandler());
//    addCadastrarListener(new CadastrarHandler());
//  }
//
//  /*
//   * Ação para consultar 
//   */
//  private class ConsultarHandler implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//      new ConsultarVooController(getConteudo(), bundle, atualizar, deletar, status);
//    }
//  }
//
//  /*
//   * Ação para cadastrar 
//   */
//  private class CadastrarHandler implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//      new CriarVooController(getConteudo(), bundle, atualizar, deletar, status);
//    }
//  }
//
// }