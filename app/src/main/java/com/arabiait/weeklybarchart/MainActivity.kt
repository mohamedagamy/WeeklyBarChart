package com.arabiait.weeklybarchart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.arabiait.werdy.utils.CustomRender
import com.arabiait.werdy.utils.dpToPx
import com.arabiait.werdy.utils.getScreenHeight
import com.arabiait.werdy.utils.showToast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawBarChart(this)
    }

    fun drawBarChart(context:Context){
        val barChart = khatma_list_barchart
        context?.let {
            val values = ArrayList<BarEntry>()
            for(i in 0..6) {
                val barEntry = BarEntry(i.toFloat(), i.toFloat())
                values.add(barEntry)
            }

            val barDataset = BarDataSet(values, " ")
            barDataset.setDrawIcons(false)
            barDataset.setDrawValues(false)
            barDataset.isHighlightEnabled = false

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(barDataset)

            val data = BarData(dataSets)
            data.setValueTextSize(12f)
            data.barWidth = 0.9f

            khatma_list_barchart.setData(data)
        }

        barChart.minimumHeight = context?.getScreenHeight()?.times(0.40f)?.toInt()
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.isDoubleTapToZoomEnabled = false
        barChart.isScaleYEnabled = false
        barChart.isScaleXEnabled = false
        barChart.isAutoScaleMinMaxEnabled = false
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.axisLeft.axisMinimum=0f
        barChart.setDrawBarShadow(false)


        val offset:Float = dpToPx(8).toFloat()
        barChart.setExtraOffsets(offset,offset,offset,offset)

        val xAxis = barChart.xAxis
        val week = arrayOf("السبت","الاحد","الاثنين","الثلاثاء","الاربعاء","الخميس","الجمعة")
        xAxis.setValueFormatter(IndexAxisValueFormatter(week))
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setDrawAxisLine(false)
        xAxis.setGranularity(1f)
        xAxis.setGranularityEnabled(true)


        xAxis.setDrawGridLines(false)
        xAxis.typeface = ResourcesCompat.getFont(context, R.font.droid_arabic_kufi)
        xAxis.textColor = ContextCompat.getColor(context, android.R.color.darker_gray)

        barChart.renderer = (CustomRender(barChart, barChart.getAnimator(), barChart.getViewPortHandler()))

        val onChartSelectedListener = object : OnChartValueSelectedListener {
            override fun onNothingSelected() {
                //Toast.makeText(context,"welcome ",Toast.LENGTH_SHORT).show()
            }

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                
            }

        }
        barChart?.setOnChartValueSelectedListener(onChartSelectedListener)
    }
}
