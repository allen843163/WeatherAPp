package com.allen.weatherapp.remote

import java.util.*
import kotlin.collections.HashSet


class ProxyRetrofitQueryMap(m: Map<String, Any>) : HashMap<String, Any>(m) {

    override val entries: MutableSet<MutableMap.MutableEntry<String, Any>>
        get() = super.entries.run {
            var newMutableSet : MutableSet<MutableMap.MutableEntry<String, Any>> = HashSet()

            this.forEach {
                if(it.value != null) {
                    if(it.value is List<*>) {
                        (it.value as List<*>).forEach {value ->
                            if(value != null) {
                                var entry : MutableMap.MutableEntry<String, Any> = SimpleEntry<String, Any>(it.key,value)
                                newMutableSet.add(entry)
                            }
                        }
                    }
                    else {
                        var entry : MutableMap.MutableEntry<String, Any> = SimpleEntry<String, Any>(it.key, it.value)
                        newMutableSet.add(entry)
                    }
                }
            }

            newMutableSet
        }

}