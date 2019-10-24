package com.verifone.kurs2.showcaseContentProviders

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.verifone.kurs2.core.repository.getDatabase

class CoffeeIntakeContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        //Timber.d("Content Provider onCreate")
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = context?.getDatabase() ?: return null
        val dao = db.coffeeIntakeDao()
        val data = dao.getAll()
        val cursor = MatrixCursor(arrayOf("id", "amount"))
        data.forEach {
            cursor.addRow(listOf(it.id, it.amount))
        }
        // Druga ciekawa technika: utworzyć MatrixCursor z kolumną "json"
        // i zserializować dane do JSONa, następnie wrzucić do MatrixCursora
        return cursor
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        // druga opcja to zwrócić "vnd.android.cursor.item" - informację, że jest to pojedynczy rezultat
        return "vnd.android.cursor.dir"
    }

}