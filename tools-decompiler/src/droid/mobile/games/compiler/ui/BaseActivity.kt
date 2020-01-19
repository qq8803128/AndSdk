package droid.mobile.games.compiler.ui

import droid.mobile.games.compiler.Pressed
import droid.mobile.games.compiler.stage
import javafx.fxml.Initializable
import javafx.scene.input.MouseEvent
import java.net.URL
import java.util.*

open abstract class BaseActivity : Initializable {
    val mPressed = Pressed()

    open fun onWindowDragged(mouseEvent: MouseEvent) {
        mouseEvent.stage().x = mouseEvent.screenX - mPressed.offsetX()
        mouseEvent.stage().y = mouseEvent.screenY - mPressed.offsetY()
    }

    open fun onWindowPressed(mouseEvent: MouseEvent) {
        mPressed.onPressed(mouseEvent)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

}
