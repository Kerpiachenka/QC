package by.gstu.app.filter

import by.gstu.app.bean.Abonent

abstract class AbonentFilter(abonents: MutableList<Abonent>) {

    val abonentsCopy: MutableList<Abonent> = ArrayList(abonents)
    abstract fun applyFilter(pattern: String?) : List<Abonent>
    abstract fun matchPattern(pattern: String, abonent: Abonent) : Boolean
}