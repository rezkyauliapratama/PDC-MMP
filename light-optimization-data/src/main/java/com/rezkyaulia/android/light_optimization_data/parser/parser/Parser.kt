/*
 *
 *  *    Copyright (C) 2016 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.rezkyaulia.android.light_optimization_data.parser.parser

import java.io.IOException
import java.lang.reflect.Type
import java.util.HashMap

import okhttp3.RequestBody
import okhttp3.ResponseBody

/**
 * Created by amitshekhar on 31/07/16.
 */
interface Parser<F, T> {

    @Throws(IOException::class)
    abstract fun convert(value: F): T

    abstract class Factory {

        abstract fun responseBodyParser(type: Type): Parser<ResponseBody, *>

        abstract fun requestBodyParser(type: Type): Parser<*, RequestBody>

        abstract fun getObject(string: String, type: Type): Any

        abstract fun getString(`object`: Any): String

        abstract fun getStringMap(`object`: Any): HashMap<String, String>

    }

}