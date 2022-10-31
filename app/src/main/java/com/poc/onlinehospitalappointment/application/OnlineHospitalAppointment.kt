package com.poc.onlinehospitalappointment.application

import android.app.Application


class OnlineHospitalAppointment : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: OnlineHospitalAppointment? = null
      /*  @JvmStatic
        lateinit var component: AppComponent*/
    }

  /*  override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().androidModule(AppModule(this)).build()
        component.inject(this)
    }
*/
}