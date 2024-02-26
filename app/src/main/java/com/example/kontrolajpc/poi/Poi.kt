package com.example.kontrolajpc.poi

import android.app.Application
import android.os.Environment
import com.example.kontrolajpc.ElectricalFaults
import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.util.Const
import com.example.kontrolajpc.util.DateUtil
import com.example.kontrolajpc.util.dataStore.PreferenceKeys
import com.example.kontrolajpc.util.dataStore.UserStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileOutputStream

/**

    1. Check for app directory in DOCUMENTS, dir name KONTROLA, create if not existing.                 DONE
    2.


 */
const val APP_DIRECTORY_NAME : String = "Kontrola"
const val ORIGINAL_EXCEL_FILE_NAME : String = "kontrola.xlsx"
val DOCUMENTS_FOLDER: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

const val C_DATUM = 1
const val C_POSEL = 2
const val C_SERIJSKA = 3
const val C_PROIZVODNI_NALOG = 4
const val C_PRIKLOP = 5
const val C_RAZDELILEC = 6
const val C_KABLI = 7
const val C_RAZVODNICA = 8
const val C_FINOMONZAZA = 9
const val C_KANAL = 10
const val C_OPOMBA = 11

const val START_ROW = 6


const val NAME_ROW = 2
const val C_NAME = 1



object Poi {
    fun export(
        faultList: List<FaultModel>,
        application: Application,
        dataStore: UserStore,
    ) {

        val appDirectory = checkForAppDir()
        val outFile = FileOutputStream(File(appDirectory,generateFileName()))


        val workBookStream = application.applicationContext.assets.open(ORIGINAL_EXCEL_FILE_NAME)

        val workBook = WorkbookFactory.create(workBookStream)

        val sheet = workBook.getSheetAt(0)

        var rowIndex = START_ROW


        GlobalScope.launch(Dispatchers.IO) {

            faultList.forEach() { faultModel ->

                //sheet.createRow(NAME_ROW)

                val name = dataStore.readStringTokenAsString(PreferenceKeys.QC_PERSON_NAME)

                val namerow = sheet.getRow(NAME_ROW)

                val nameCell = namerow.getCell(C_NAME)


                val currentRow = sheet.getRow(rowIndex)

                val cellDate = currentRow.getCell(C_DATUM)
                val cellPosel = currentRow.getCell(C_POSEL)
                val cellSerijska = currentRow.getCell(C_SERIJSKA)
                val cellProizvodniNalog = currentRow.getCell(C_PROIZVODNI_NALOG)
                val cellOpomba = currentRow.getCell(C_OPOMBA)

                cellDate.setCellValue(DateUtil.fromLongToDate(faultModel.datum,"dd. MM. yyyy"))
                cellPosel.setCellValue(faultModel.posel)
                cellSerijska.setCellValue(faultModel.serijska)
                cellProizvodniNalog.setCellValue(faultModel.proizvodniNalog)

                val faultCellPos = when (faultModel.vrstaNapake) {
                    ElectricalFaults.Cee_Priklop.ordinal -> C_PRIKLOP
                    ElectricalFaults.Razdelilec.ordinal -> C_RAZDELILEC
                    ElectricalFaults.Kabli.ordinal -> C_KABLI
                    ElectricalFaults.Vezava_Razvodnice.ordinal -> C_RAZVODNICA
                    ElectricalFaults.Finomontaža.ordinal -> C_FINOMONZAZA
                    ElectricalFaults.Kanal.ordinal -> C_KANAL

                    else -> { 5 }
                }

                val faultCell = currentRow.getCell(faultCellPos)

                faultCell.setCellValue("X")

                nameCell.setCellValue(name)


                cellOpomba.setCellValue(faultModel.opisNapake)
                rowIndex++
            }
            workBook.write(outFile)
            outFile.close()
        }
    }

    private fun generateFileName(): String {
        val date = DateUtil.cDateWithFormat(Const.DATE_FORMAT_EXPORT)
        return "Kontrola$date.xlsx"
    }

    private fun checkForAppDir(): File? {
        val appDir = File(DOCUMENTS_FOLDER, APP_DIRECTORY_NAME)

        return if (appDir.mkdirs() || appDir.isDirectory) {
            appDir
        } else {
            null
        }
    }
}
