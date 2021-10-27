package com.caitlykate.bluetoothmonitor

import android.bluetooth.BluetoothAdapter

class BtConnection(private val adapter: BluetoothAdapter) {

    lateinit var cThread: ConnectThread

    fun connect(mac: String){
        if (adapter.isEnabled && mac.isNotEmpty()) {
            val device = adapter.getRemoteDevice(mac)
            device.let {
                //соединение производим на 2-степенном потоке
                cThread = ConnectThread(it)
                cThread.start()
            }
        }
    }


}