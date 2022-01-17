package icu.yuyurbq.project.util

object RandomUtil {

    private val defaultTable = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    /**
     * 返回指定长度的随机字符串，不包含字符
     */
    fun getStr(size: Int, table: List<Char> = defaultTable): String = List(size) { table.random() }.joinToString("")

}