package com.ahold.triggers.trigger2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trigger2")
class Trigger1Controller {

    @GetMapping()
    fun getCanceledOrders(
        @RequestParam("trigger")
        trigger1Value: String
    ) = Trigger2(trigger1Value)
}

data class Trigger2(
    val value:String
)