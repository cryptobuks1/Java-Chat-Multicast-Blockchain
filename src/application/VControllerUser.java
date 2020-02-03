package application;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import server.Server;

public class VControllerUser extends Controller {
	private Server server;
	@FXML
	private TextField txtOld;
	@FXML
	private TextField txtNew;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public VControllerUser() {
		Server server = new Server();
		this.server = server;
	}

	private void updateUser() {
		// try {
		// server.updateUser(number, username, password, name)(getId(),
		// txtNueva.getText(), txtName.getText(), txtSurname.getText());
		System.out.println("Updating user");
		// } catch (RemoteException e) {
		dialog(AlertType.WARNING, "Ha ocurrido algo", "Fallo modificando los datos",
				"Pruebe a introducir de nuevo los datos, si el error persiste, introduce unos nuevos");
		// e.printStackTrace();
		// }
	}

	public void clickSave(ActionEvent event) {
		try {
			if (txtOld.getText() != txtNew.getText()) {
				updateUser();
				dialog(AlertType.INFORMATION, "EXIT", "Cambios realizados", "");
			} else {
				dialog(AlertType.WARNING, "Datos introduccidos no aceptados", "La contraseņa es igual a la anterior",
						"Pruebe a introducir otra contraseņa diferente");
			}

		} catch (Exception e) {
			dialog(AlertType.ERROR, "Error", "Error", "Ha habido un error al guardar los cambios");
		}
		Stage stage = (Stage) btnSave.getScene().getWindow();
		stage.close();
		// server.refreshContactos();
	}

	public void clickCancel(ActionEvent event) {
		cancelar(event);
	}

	public void clickDelete(ActionEvent event) {
		try {
			System.out.println("Deleting user");
			server.deleteUser(Integer.parseInt(getId()));
			Stage stage = (Stage) btnSave.getScene().getWindow();
			stage.close();
			// server.de("DELETE FROM contacts WHERE ref_user LIKE '"+getId()+"'");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
