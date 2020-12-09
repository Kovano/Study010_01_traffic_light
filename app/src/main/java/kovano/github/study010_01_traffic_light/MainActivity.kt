package kovano.github.study010_01_traffic_light

/*
+1.Убрать панель верхнюю,
строка
class MainActivity : Activity() {
и не забываю
import android.app.Activity

+2. Установка приложения на карту памяти, в манифесте строка
<application
android:installLocation="preferExternal"
</application>

+3. Убрать верхний статус бар, вот эта строка
<item name="android:windowFullscreen">true</item>
путь файла
res\values\themes.xml
внутри блока
<style name="Theme.Study010_03" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
</style>

+4. подгрузка картинок автоматом цеплается через android:src="@drawable/ХХХХ"
потому что картинки добавляются после (по времени) объявления class MainActivity : Activity()
android:src="@drawable/semafor_grey"
*/

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*



class MainActivity : Activity() {

    var imSemafor : ImageView? = null
    var counter: Int = 0
    var timer: Timer? = null
    var is_run:Boolean = false
    var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imSemafor = findViewById(R.id.imSemafor)
    }

    fun onClickStartStop (view: View)
    {
        view as ImageButton
        if (!is_run) {
            timer=Timer()
            startStop()
            view.setImageResource(R.drawable.button_stop)
            is_run=true
        }
        else{
            imSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            is_run=false
            counter = 0
        }
    }

    fun startStop()
    {
        timer=Timer()
        timer?.schedule(object :TimerTask(){
            override fun run() {
                runOnUiThread{
                    imSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) counter=0
                }

            }

        },0, 1000)
    }

}