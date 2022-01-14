package com.example

import com.example.app.others.di.mainModule
import io.ktor.application.*
import com.example.plugins.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.ktor.ext.Koin
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@OptIn(ExperimentalTime::class)
fun Application.main(){
/*    launch {
        while (true){
            delay(1000)
            print("1 sec")
        }
    }*/
}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    main()
    install(Koin){
        modules(mainModule)
    }
    configureRouting()
    configureSerialization()
    configureHTTP()
    configureSecurity()

    /* add auth here */
}
