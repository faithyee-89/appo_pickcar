package com.appotronics.appo_car_app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appotronics.appo_car_app.api.APIs
import com.appotronics.appo_car_app.api.DidiService
import com.appotronics.appo_car_app.bean.CallCarResult
import com.appotronics.appo_car_app.bean.CommonResult
import com.appotronics.appo_car_app.bean.LoginResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var retrofit: Retrofit? = null
    private var token: String? = null
    private var orderId: String? = null
    private var isLoginCarPlatform: Boolean = false
    private var isCallCar: Boolean = false
    private var isTest: String = "1"//1走开发线路 0走胡工演示线路 2我自己用
    private var targetGsn : String = "dce9ddc7-fe81-483f-9ec4-6f78f34a3066" //APPO-000006 投影机X2000_cn
//    private var targetGsn : String = "f56d3bdd-a082-4852-89ec-6f3229e8df27" //APPO-000007
//    private var targetGsn : String = "9c2f153d-7837-4e29-9b7a-5a2a2e7cc1f1" //APPO-000002 我自己用
//    ""//

    fun initHttpConfig() {
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(APIs.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initHttpConfig()
    }

    fun loginCarPlatform(view: View?) {
        isLoginCarPlatform = false
        val login = retrofit?.create(DidiService::class.java)?.login("17512067574", "1","1", isTest)
        login?.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                MqttLogUtils.i("${response}")
                token = response.body()?.data?.token
                isLoginCarPlatform = true
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun loginPrivateCar(view : View?) {
        isLoginCarPlatform = false
        val login = retrofit?.create(DidiService::class.java)?.login("17512067574", "1","2", isTest)
        login?.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                MqttLogUtils.i("${response}")
                token = response.body()?.data?.token
                isLoginCarPlatform = true
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun callCar(view: View?) {
        isCallCar = false
        if (!isLoginCarPlatform) {
            Toast.makeText(this, "请先登录打车平台", Toast.LENGTH_SHORT).show()
            return
        }
        val login =
            retrofit?.create(DidiService::class.java)?.callCar(token, "深圳湾总部大厦", "联合总部大厦", isTest)
        login?.enqueue(object : Callback<CallCarResult> {
            override fun onResponse(call: Call<CallCarResult>, response: Response<CallCarResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
                orderId = response.body()?.data?.orderId
                isCallCar = true
            }

            override fun onFailure(call: Call<CallCarResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun pickOrder(view: View?) {
        if (!isCallCar) {
            Toast.makeText(this, "请先叫车", Toast.LENGTH_SHORT).show()
            return
        }
        val login = retrofit?.create(DidiService::class.java)?.pickOrder(token, orderId, isTest)
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun arriveStart(view: View?) {
        if (!isCallCar) {
            Toast.makeText(this, "请先叫车", Toast.LENGTH_SHORT).show()
            return
        }
        val login = retrofit?.create(DidiService::class.java)?.arriveStart(token, orderId, isTest)
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun getOnCar(view: View?) {
        if (!isCallCar) {
            Toast.makeText(this, "请先叫车", Toast.LENGTH_SHORT).show()
            return
        }
        val login = retrofit?.create(DidiService::class.java)?.getOnCar(token, orderId, isTest)
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun arriveEnd(view: View?) {
        if (!isCallCar) {
            Toast.makeText(this, "请先叫车", Toast.LENGTH_SHORT).show()
            return
        }
        val login = retrofit?.create(DidiService::class.java)?.arriveEnd(token, orderId, isTest)
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun payOrder(view: View?) {
        if (!isCallCar) {
            Toast.makeText(this, "请先叫车", Toast.LENGTH_SHORT).show()
            return
        }
        val login = retrofit?.create(DidiService::class.java)?.payOrder(token, orderId, isTest)
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun mediaTempV1(view: View?) {
        val login = retrofit?.create(DidiService::class.java)?.sendMediaTemp(targetGsn, "gf_01")
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun mediaTempV2(view: View?) {
        val login = retrofit?.create(DidiService::class.java)?.sendMediaTemp(targetGsn, "gf_02")
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun mediaTempV3(view: View?) {
        val login = retrofit?.create(DidiService::class.java)?.sendMediaTemp(targetGsn, "gf_03")
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }

    fun mediaTempV4(view: View?) {
        val login = retrofit?.create(DidiService::class.java)?.sendMediaTemp(targetGsn, "gf_04")
        login?.enqueue(object : Callback<CommonResult> {
            override fun onResponse(call: Call<CommonResult>, response: Response<CommonResult>) {
                MqttLogUtils.i("${response.body()?.msg}")
            }

            override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                MqttLogUtils.i(t.message)
            }
        })
    }
}