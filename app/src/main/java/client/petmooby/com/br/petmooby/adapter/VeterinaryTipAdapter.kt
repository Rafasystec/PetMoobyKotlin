package client.petmooby.com.br.petmooby.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import client.petmooby.com.br.petmooby.R
import client.petmooby.com.br.petmooby.model.VeterinaryTip
import com.squareup.picasso.Picasso

/**
 * Created by idoctor on 16/05/2019.
 */
class VeterinaryTipAdapter(
        val vets: List<VeterinaryTip>,
        val onClick: (VeterinaryTip) -> Unit) : RecyclerView.Adapter<VeterinaryTipAdapter.VetViewHolder>(){

    override fun onBindViewHolder(holder: VetViewHolder, position: Int) {
        val context = holder.itemView.context
        val vet = vets[position]
        holder.tvNameVet.text = vet.name
        holder.tvEmailVet.text = vet.email

        Picasso.with(context).load(vet.photo).placeholder(R.drawable.ic_paw_24).fit().into(holder.ivProfileVet,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        //Stop progress bar
                    }

                    override fun onError() {
                        //Stop progress bar
                    }

                })

        holder.itemView.setOnClickListener { onClick(vet) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_vet_partners_list,parent,false)

        return VetViewHolder(view)
    }

    override fun getItemCount() = this.vets.size

    class VetViewHolder(view: View): RecyclerView.ViewHolder(view){
        var cardView        = view.findViewById<CardView>(R.id.cvVetPartnersList)
        var ivProfileVet    = view.findViewById<ImageView>(R.id.ivProfileVet)
        var tvNameVet       = view.findViewById<TextView>(R.id.tvNameVet)
        var tvEmailVet      = view.findViewById<TextView>(R.id.tvEmailVet)

    }

}