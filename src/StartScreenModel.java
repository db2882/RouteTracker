import java.util.ArrayList;

import javax.swing.DefaultListModel;


public class StartScreenModel {
	private DefaultListModel listModel;

	StartScreenModel(){
		listModel = new DefaultListModel();
	}

	public DefaultListModel getListModel(){
		return listModel;
	}
	
	public void setListModel(DefaultListModel list){
		listModel = list;
	}
	
	public void setListModel(ArrayList<String> list){
		for(String s : list){
			listModel.addElement(s);
		}
	}
}
