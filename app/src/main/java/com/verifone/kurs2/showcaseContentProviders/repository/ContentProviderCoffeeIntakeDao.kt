/*
package com.verifone.kurs2.showcaseContentProviders.repository

import android.content.Context
import android.net.Uri
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ContentProviderCoffeeIntakeDao(
    context: Context
) : CoffeeIntakeDao {

    private val resolver = context.contentResolver

    override fun insert(coffeeIntake: CoffeeIntake): Long = 0

    override fun update(coffeeIntake: CoffeeIntake): Int = 0

    override fun delete(coffeeIntake: CoffeeIntake): Int = 0

    override fun getAll(): List<CoffeeIntake> {
        // Tutaj wiemy jakie dane możemy pobrać, bo sami napisaliśmy ContentProvidera
        // Gdybyśmy chcieli wyciągnąć coś z systemu, to poszlibyśmy na stronę
        // https://developer.android.com/reference/android/provider/package-summary
        // i przeczytali dokumentację :)

        val authority = "com.verifone.kurs2.provider"
        val uri = Uri.parse("content://$authority/")
        val cursor = resolver.query(uri, null ,null, null, null)

        val result = mutableListOf<CoffeeIntake>()

        cursor?.use {
            if (it.moveToFirst()) {

                val idColumn = cursor.getColumnIndex("id")
                val amountColumn = cursor.getColumnIndex("amount")

                do {
                    val id = cursor.getLong(idColumn)
                    val amount = cursor.getFloat(amountColumn)
                    val intake = CoffeeIntake(id, amount)

                    result.add(intake)
                } while (it.moveToNext())

            }
        }

        return result
    }

    override fun observeAll(): Flowable<List<CoffeeIntake>> {
        return Flowable.fromCallable {
            getAll()
        }.subscribeOn(Schedulers.io())
    }
}
*/
