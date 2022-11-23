package com.example.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.model.Person

class PersonAdapter(
    context: Context,
    private val personList: MutableList<Person>,
    private val forCalculate: Boolean = false
) : ArrayAdapter<Person>(context, R.layout.tile_person, personList) {
    private data class TilePersonHolder(
        val nameTv: TextView, val paidValueTv: TextView, val valueToPayTv: TextView, val valueToReceiveTv: TextView
        )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val person = personList[position]

        var personTileView = convertView

        if (personTileView == null) {

            personTileView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_person,
                    parent,
                    false
                )

            val tilePersonHolder = TilePersonHolder(
                personTileView.findViewById(R.id.nameTv),
                personTileView.findViewById(R.id.paidValueTv),
                personTileView.findViewById(R.id.valueToPayTv),
                personTileView.findViewById(R.id.valueToReceiveTv)
            )
            personTileView.tag = tilePersonHolder
        }

        with(personTileView?.tag as TilePersonHolder) {
            nameTv.text = person.name
            paidValueTv.text = String.format("Pagou: R$ %.2f", person.paidValue)
            if (forCalculate) {
                valueToPayTv.text = String.format("Deve Pagar: R$ %.2f", person.valueToPay)
                valueToReceiveTv.text = String.format("Deve Receber: R$ %.2f", person.valueToReceive)
            } else {
                valueToPayTv.visibility = View.GONE
                valueToReceiveTv.visibility = View.GONE
            }
        }

        return personTileView
    }
}
