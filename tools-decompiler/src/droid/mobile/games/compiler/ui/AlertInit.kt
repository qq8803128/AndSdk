package droid.mobile.games.compiler.ui

import droid.mobile.games.compiler.Launcher
import droid.mobile.games.compiler.stage
import droid.mobile.games.compiler.util.FileUtils
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.io.File

import java.net.URL
import java.util.ResourceBundle


class AlertInit : Initializable {
    @FXML
    internal var mCloseInit: Label? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        Observable.create(ObservableOnSubscribe<Int> { observableEmitter ->
            val file = File("${FileUtils.getWorkSpace()}","init.cfg")
            if (!file.exists() || !file.isFile){
                val `is` = Launcher.javaClass.getResourceAsStream("WorkSpace.zip")
            }
            observableEmitter.onNext(1)
        }).subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe {
                    mCloseInit?.stage()?.close()
                }

    }
}
