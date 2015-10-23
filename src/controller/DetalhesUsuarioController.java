 
//package  controller;
//
//import java.util.List;
//import java.util.ResourceBundle;
//
//import javax.swing.JPanel;
//import javax.swing.JTable;
//
//import  model.PessoaFisica;
//import  model.Usuario;
//import  view.usuario.DetalhesUsuarioUI;
//
 
//public class DetalhesUsuarioController extends DetalhesUsuarioUI {
//
//  private static final DetalhesUsuarioController ui = new DetalhesUsuarioController();
//  private boolean result;
//  private List<Usuario> list;
//  private JTable tabela;
//
//  private Usuario pojo;
//  private PessoaFisica pojoPF;
//
//  private DetalhesUsuarioController() {
//  }
//
//  public static DetalhesUsuarioController getInstance() {
//    return ui;
//  }
//
//  public void setAttributes(JTable tabela,
//                            JPanel subConteudo,
//                            ResourceBundle bundle) {
//
//    this.tabela = tabela;
//
//    setAttributes(subConteudo, bundle);
//    detalhesHandler();
//  }
//
//  public void setResult(boolean result) {
//    this.result = result;
//  }
//
//  public void setList(List<Usuario> list) {
//    this.list = list;
//  }
//
//  private void detalhesHandler() {
//
//    if (!result) {
//      result = true;
//      int[] rows = tabela.getSelectedRows();
//
//      if (rows.length == 1) {
//        pojo = list.get(rows[0]);
//        pojoPF = pojo.getPessoaFisica();
//
//        setParameters(pojoPF, pojo);
//        showAll();
//      } else {
//        messageFailed();
//        refresh();
//      }
//    }
//  }
// }