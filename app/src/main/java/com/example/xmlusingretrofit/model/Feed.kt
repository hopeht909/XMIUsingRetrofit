package com.example.xmlusingretrofit.model


import com.example.xmlusingretrofit.model.entry.Entry
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable


@Root(name = "feed", strict = false)
class Feed constructor() : Serializable   {
    @field:Element(name = "icon")
    var icon: String? = null

    @field:Element(name = "id")
    var id: String? = null

    @field:Element(name = "logo")
    var logo: String? = null

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "updated")
    var updated: String? = null

    @field:Element(name = "subtitle")
    var subtitle: String? = null

    @field:ElementList(inline = true, name = "entry")
    var entrys: List<Entry>? = null


}