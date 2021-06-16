package by.gstu.app.filter

import by.gstu.app.bean.Abonent
import java.util.*

class AbonentFilterImpl(abonents: MutableList<Abonent>) : AbonentFilter(abonents) {
    override fun applyFilter(pattern: String?): List<Abonent> {
        if (isPatternInvalid(pattern))
            return abonentsCopy

        val preparedPattern : String = pattern.toString().toLowerCase(Locale.ROOT).trim()
        val filteredList : MutableList<Abonent> = mutableListOf()
        for (Abonent in abonentsCopy)
            if (matchPattern(preparedPattern, Abonent))
                filteredList.add(Abonent)

        return filteredList
    }

    override fun matchPattern(pattern: String, abonent: Abonent) : Boolean {
        return abonent.name.toLowerCase(Locale.ROOT).contains(pattern)
    }

    private fun isPatternInvalid(pattern: String?) : Boolean {
        return pattern == null || pattern.isEmpty()
    }
}