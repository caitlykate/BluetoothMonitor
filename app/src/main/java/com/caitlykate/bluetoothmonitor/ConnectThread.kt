package com.caitlykate.bluetoothmonitor

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.*

//создаем сокет - канал связи между микроконтроллером и нашим устройством
class ConnectThread(private val device: BluetoothDevice): Thread() {

    val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    var mSocket: BluetoothSocket? = null

    init {
        try {
            //создаем подключение, указываем его код
            mSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        } catch (i: IOException){

        }
    }

    //подключаемся к устройству, кот. получили по мак-адресу
    override fun run() {
        try {
            Log.d("MyLog","Connecting... ")
            mSocket?.connect()
            Log.d("MyLog","Connected")
        } catch (i: IOException){
            Log.d("MyLog","Can not connect to device")
        }
    }

    fun closeConnection(){
        try{
            mSocket?.close()
        } catch (i: IOException){

        }
    }
}