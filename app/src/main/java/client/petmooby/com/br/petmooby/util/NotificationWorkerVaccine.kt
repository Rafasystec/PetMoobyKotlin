package client.petmooby.com.br.petmooby.util

import android.content.Context
import androidx.annotation.NonNull
import androidx.work.Worker
import androidx.work.WorkerParameters
import client.petmooby.com.br.petmooby.model.enums.EnumTypeEvent


class NotificationWorkerVaccine(@NonNull context: Context, @NonNull  params: WorkerParameters): Worker(context,params) {

    private val parameters = params
    override fun doWork(): Result {
        val param = ParametersEvent()
        with(parameters.inputData){
            param.id = getLong("id",0L)
            param.animalName = getString("name")!!
            param.dateString = getString("date")!!
            param.vaccineType = getString("vaccineType")!!
        }
        param.type = EnumTypeEvent.VACCINE
        NotificationUtil.notify(super.getApplicationContext(),param)
        return Result.success()
    }


}