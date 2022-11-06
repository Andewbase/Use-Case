package z.nova.usecase1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import z.nova.data.repository.UserRepositoryImpl
import z.nova.data.storage.sharedprefs.SharedPrefUserStorage
import z.nova.usecase1.domain.models.SaveUserNameParam
import z.nova.usecase1.domain.models.UserName
import z.nova.usecase1.domain.usecase.GetUserNameUseCase
import z.nova.usecase1.domain.usecase.SaveUserNameUseCase

import z.nova.usecase3model.R

class MainActivity : AppCompatActivity() {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE)
    { UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = applicationContext)) }

    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE)
    { GetUserNameUseCase(userRepository = userRepository) }

    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE)
    { SaveUserNameUseCase(userRepository = userRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        sendButton.setOnClickListener{

            val text = dataEditText.text.toString()
            val params = SaveUserNameParam(name = text)
            val result: Boolean = saveUserNameUseCase.execute(param = params)
            dataTextView.text = "Save result = $result"
        }

        receiveButton.setOnClickListener{

            val userName: UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.lastName}"

        }
    }




}