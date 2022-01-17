package icu.yuyurbq.project.util

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESUtil {

    private var KEY = "123456789abandon"
    private var IV = "abandon123456789"

    /**
     * 加密
     */
    fun encrypt(data: String, key: String = KEY, iv: String = IV): String? {
        return try {
            val cipher = Cipher.getInstance("AES/CBC/NoPadding")
            val blockSize = cipher.blockSize
            val dataBytes = data.toByteArray()
            var plaintextLength = dataBytes.size
            if (plaintextLength % blockSize != 0) {
                plaintextLength += (blockSize - plaintextLength % blockSize)
            }

            val plaintext = ByteArray(plaintextLength)
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.size)

            val keySpec = SecretKeySpec(key.toByteArray(), "AES")
            val ivSpec = IvParameterSpec(iv.toByteArray())
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
            val encrypted = cipher.doFinal(plaintext)
            Base64.getEncoder().encodeToString(encrypted)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 解密
     */
    fun decrypt(data: String, key: String = KEY, iv: String = IV): String? {
        return try {
            val cipher = Cipher.getInstance("AES/CBC/NoPadding")
            val keySpec = SecretKeySpec(key.toByteArray(), "AES")
            val ivSpec = IvParameterSpec(iv.toByteArray())
            val encrypted = Base64.getDecoder().decode(data)
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            val original = cipher.doFinal(encrypted)
            var last = 0
            for (i in original.size - 1 downTo 1) {
                if (original[i] != 0.toByte()) {
                    last = i
                    break
                }
            }
            String(original, 0, last + 1)
        } catch (e: Exception) {
            null
        }
    }

}