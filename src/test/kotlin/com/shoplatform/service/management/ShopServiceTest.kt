package com.shoplatform.service.management

import com.navercorp.fixturemonkey.kotlin.giveMeOne
import com.shoplatform.FixtureMonkeyBuilder.fixtureMonkey
import com.shoplatform.dto.management.ShopDto
import com.shoplatform.repository.ShopRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ShopServiceTest: BehaviorSpec({

    val shopRepository: ShopRepository = mockk()
    val shopService = ShopService(shopRepository)

    Context("매장 등록") {
        Given("기존에 매장이 이미 존재하는 경우") {
            val request: ShopDto.Request = fixtureMonkey.giveMeOne()
            every { shopService.existsShopEntity(request.shop.code) } returns true

            When("매장 등록") {
                val exception = shouldThrow<IllegalArgumentException> {
                    shopService.create(request)
                }

                Then("예외 반환") {
                    verify(exactly = 1) { shopService.existsShopEntity(request.shop.code) }
                    // TODO 에러 코드화하기
                    exception.message shouldContain "already exists"
                }
            }
        }

        Given("기존에 매장이 없는 경우") {
            When("매장 등록") {
                Then("매장 등록 성공") {

                }
            }
        }
    }

    Context("매장 조회") {
        Given("매장이 있는 경우") {
            When("매장 조회") {
                Then("매장 정보 반환") {

                }
            }
        }

        Given("매장이 없는 경우") {
            When("매장 조회") {
                Then("예외 반환") {

                }
            }
        }
    }

    Context("매장 수정") {
        Given("매장이 있는 경우") {
            When("매장 수정") {
                Then("정상 등록") {

                }
            }
        }

        Given("매장이 없는 경우") {
            When("매장 수정") {
                Then("예외 반환") {

                }
            }
        }
    }

    Context("매장 등록/수정(Upsert)") {
        Given("매장이 있는 경우") {
            When("매장 업서트") {
                Then("매장 수정") {

                }
            }
        }

        Given("매장이 없는 경우") {
            When("매장 업서트") {
                Then("매장 등록") {

                }
            }
        }
    }

    Context("매장 삭제") {
        Given("매장이 있는 경우") {
            When("매장 삭제") {
                Then("매장 삭제 성공") {

                }
            }
        }

        Given("매장이 없는 경우") {
            When("매장 삭제") {
                Then("예외 발생") {

                }
            }
        }
    }
})