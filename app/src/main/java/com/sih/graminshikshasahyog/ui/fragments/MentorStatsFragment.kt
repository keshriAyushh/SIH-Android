package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentMentorStatsBinding

class MentorStatsFragment : Fragment() {

    private lateinit var binding: FragmentMentorStatsBinding
    private lateinit var lineChart: LineChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorStatsBinding.inflate(layoutInflater)

        lineChart = binding.lineChart

        val lineDataSet1 = LineDataSet(dataValues(), "Data Set 1")

        val dataSets: ArrayList<LineDataSet> = arrayListOf()
        dataSets.add(lineDataSet1)

        val data: LineData = LineData(lineDataSet1)
        lineChart.data = data
        lineChart.invalidate()


        return binding.root
    }

    private fun dataValues(): ArrayList<Entry> {
        val dataVals: ArrayList<Entry> = arrayListOf()

        dataVals.add(Entry(0F, 20F))
        dataVals.add(Entry(1F, 24F))
        dataVals.add(Entry(2F, 12F))
        dataVals.add(Entry(3F, 36F))

        return dataVals
    }

}