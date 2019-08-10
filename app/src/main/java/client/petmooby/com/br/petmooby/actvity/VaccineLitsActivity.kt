package client.petmooby.com.br.petmooby.actvity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import client.petmooby.com.br.petmooby.R
import client.petmooby.com.br.petmooby.adapter.VaccineAdapter
import client.petmooby.com.br.petmooby.extensions.setupToolbar
import client.petmooby.com.br.petmooby.model.Animal
import client.petmooby.com.br.petmooby.util.Parameters
import client.petmooby.com.br.petmooby.util.ResultCodes
import kotlinx.android.synthetic.main.activity_vaccine_lits.*

class VaccineLitsActivity : BaseActivity() {


    var animal:Animal?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine_lits)
        setupToolbar(R.id.toolbarVaccineList, R.string.vaccines)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menuAdd -> {
            val intent = Intent(this,VaccineActivity::class.java)
            intent.putExtra(Parameters.ACTION,ResultCodes.REQUEST_ADD_VACCINE)
            intent.putExtra(Parameters.ANIMAL_PARAMETER,animal!!)
            startActivityForResult(intent,ResultCodes.REQUEST_ADD_VACCINE)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun getPetVaccines(){
         if(animal?.vaccineCards != null){
             rcViewVaccineList.adapter = VaccineAdapter(animal?.vaccineCards!!,{vaccineCards -> onClick(vaccineCards) })
         }
    }

    private fun onClick(vaccineCards: Animal.VaccineCards){
        var intent = Intent(this,VaccineActivity::class.java)
        intent.putExtra(Parameters.ACTION,ResultCodes.REQUEST_UPDATE_VACCINE)
        intent.putExtra(Parameters.VACCINE_CARD,vaccineCards)
        startActivityForResult(intent, ResultCodes.REQUEST_UPDATE_VACCINE)
    }
}
