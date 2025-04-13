package com.shoplatform

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.generator.DefaultNullInjectGenerator
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector
import com.navercorp.fixturemonkey.api.introspector.FailoverIntrospector
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector
import com.navercorp.fixturemonkey.kotest.KotestPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin

object FixtureMonkeyBuilder {
    val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .plugin(KotestPlugin())
        .defaultNullInjectGenerator { DefaultNullInjectGenerator.NOT_NULL_INJECT }
//        .defaultNotNull(true)
        .objectIntrospector(
            FailoverIntrospector(
                listOf(
                    FieldReflectionArbitraryIntrospector.INSTANCE,
                    ConstructorPropertiesArbitraryIntrospector.INSTANCE,
                )
            )
        )
        .build()
}