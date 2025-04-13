package com.shoplatform.service.management

import com.shoplatform.repository.CategoryRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk

class CategoryServiceTest: BehaviorSpec({

    val categoryRepository: CategoryRepository = mockk()
    val shopService: ShopService = mockk()
    val categoryService = CategoryService(shopService, categoryRepository)

    Context("카테고리 등록") {
        Given("매장이 존재하지 않는 경우") {
            When("카테고리 등록") {
                Then("예외 반환") {

                }
            }
        }

        Given("목록 중 적어도 하나의 카테고리가 이미 존재하는 경우") {
            When("카테고리 등록") {
                Then("예외 반환") {

                }
            }
        }

        Given("카테고리가 존재하지 않는 경우") {
            When("카테고리 등록") {
                Then("카테고리 등록 성공") {

                }
            }
        }
    }

    Context("카테고리 조회") {
        Given("매장이 존재하지 않는 경우") {
            When("카테고리 조회") {
                Then("예외 반환") {

                }
            }
        }

        Given("매장이 존재하는 경우") {
            When("카테고리 조회") {
                Then("매장내 모든 카테고리 리스트 반환") {

                }
            }
        }
    }

    Context("카테고리 수정") {
        Given("매장이 존재하지 않는 경우") {
            When("카테고리 수정") {
                Then("예외 반환") {

                }
            }
        }

        Given("카테고리가 없는 경우") {
            When("카테고리 수정") {
                Then("예외 반환") {

                }
            }
        }

        Given("카테고리가 있는 경우") {
            When("카테고리 수정") {
                Then("카테고리 수정 성공") {

                }
            }
        }
    }

    Context("카테고리 업서트") {
        Given("매장이 없는 경우") {
            When("카테고리 업서트") {
                Then("예외 반환") {

                }
            }
        }

        Given("카테고리가 없는 경우") {
            When("카테고리 업서트") {
                Then("카테고리 등록 성공") {

                }
            }
        }

        Given("카테고리가 있는 경우") {
            When("카테고리 업서트") {
                Then("카테고리 수정 성공") {

                }
            }
        }
    }

    Context("카테고리 삭제") {
        Given("매장이 없는 경우") {
            When("카테고리 삭제") {
                Then("예외 반환") {

                }
            }
        }

        Given("카테고리가 있는 경우") {
            When("카테고리 삭제") {
                Then("삭제 성공") {

                }
            }
        }

        Given("카테고리가 없는 경우") {
            When("카테고리 삭제") {
                Then("예외를 반환하지 않고 성공") {

                }
            }
        }
    }
})