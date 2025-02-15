package com.example.fcsecurekeyboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.intellij.lang.annotations.Pattern

class PinViewModel : ViewModel() {
    val passwordLiveData: LiveData<CharSequence> by lazy { _passwordLiveData }

    private val _passwordLiveData by lazy { MutableLiveData<CharSequence>() }

    val messageLivedata: LiveData<String> by lazy { _messageLivedata }
    private val _messageLivedata by lazy { MutableLiveData<String>() }

    private val password: StringBuffer = StringBuffer("")

    fun input(num: String) {
        if (password.length < 6) {
            password.append(num)
            _passwordLiveData.value = password.toString()
        }
    }

    fun delete() {
        if (password.isNotEmpty()) {
            password.deleteCharAt(password.length - 1)
            _passwordLiveData.value = password.toString()
        }
    }

    fun done() {
        if (validPin()) {
            _messageLivedata.value = "설정한 비밀번호는 $password 입니다."
            reset()
        }
    }

    private fun reset() {
        password.replace(0, password.length, "")
        _passwordLiveData.value = password.toString()
    }

    private fun validPin(): Boolean {
        if (password.length < 6) {
            _messageLivedata.value = "비밀번호 6자리를 입력해주세요."
            return false
        }

        if (java.util.regex.Pattern.compile("(\\w)\\1\\1").matcher(password.toString()).find()) {
            _messageLivedata.value = "3자리 이상 같은 숫자는 사용할 수 없습니다."
            reset()
            return false
        }

        var count = 0

        password.reduce { before, after ->
            if (after - before == 1) {
                count++
                if (count >= 2) {
                    _messageLivedata.value = "연속 된 3자리 숫자는 사용하실 수 없습니다."
                    reset()
                    return false
                }
            } else {
                count = 0
            }
            after
        }

        return true
    }

}