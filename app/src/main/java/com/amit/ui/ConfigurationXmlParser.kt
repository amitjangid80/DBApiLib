package com.amit.ui

import android.content.Context
import android.content.res.XmlResourceParser
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import java.lang.Exception

/**
 * Created by AMIT JANGID on 12/03/2019.
**/
class ConfigurationXmlParser(private val context: Context, xmlRes: Int)
{
    private val TAG = ConfigurationXmlParser::class.java.simpleName
    private val parser: XmlResourceParser = context.resources.getXml(xmlRes)
    private val itemConfigList: ArrayList<BottomAppBarItemConfig> = arrayListOf()

    companion object
    {
        const val KEY_TEXT: String = "text"
        const val KEY_DRAWABLE: String = "drawable"
        const val KEY_TAB: String = "tab"
    }

    fun parse(): ArrayList<BottomAppBarItemConfig>
    {
        itemConfigList.clear()

        try
        {
            var eventType: Int?

            do
            {
                eventType = parser.next();

                if (eventType == XmlResourceParser.START_TAG && KEY_TAB == parser.name)
                {
                    itemConfigList.add(getTabConfig(parser))
                }
            } while (eventType != XmlResourceParser.END_DOCUMENT)
        }
        catch (e:Exception)
        {
            Log.e(TAG, "exception while parsing xml resource")
            e.printStackTrace()
        }

        return itemConfigList
    }

    private fun getTabConfig(parser: XmlResourceParser):BottomAppBarItemConfig
    {
        val attributeCount = parser.attributeCount
        var itemText: String? = null
        var itemDrawable: Drawable? = null

        for (i in 0 until attributeCount)
        {
            when (parser.getAttributeName(i))
            {
                KEY_TEXT -> itemText = getText(parser, i)
                KEY_DRAWABLE -> itemDrawable = getDrawable(parser, i)
            }
        }

        return BottomAppBarItemConfig(text = itemText!!, drawable = itemDrawable!!, index = itemConfigList.size, selected = false)
    }

    private fun getDrawable(parser: XmlResourceParser, i: Int): Drawable
    {
        return ContextCompat.getDrawable(context, parser.getAttributeResourceValue(i, 0))!!
    }

    private fun getText(parser: XmlResourceParser, i: Int): String
    {
        return context.getString(parser.getAttributeResourceValue(i, 0))
    }
}
