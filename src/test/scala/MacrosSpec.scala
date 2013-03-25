package com.ubiregi.commons

import org.specs2.mutable.Specification

class MacrosSpec extends Specification{

  "Macros" should {
    "assertEqual" in {
      Macros.assertEqual((1 to 10).sum,0) must throwA[Exception].like{case e =>
        e.getMessage must_== "AssertionError: scala.this.Predef.intWrapper(1).to(10).sum[Int](math.this.Numeric.IntIsIntegral) [55] is not equal to 0 [0]"
      }

      // should not throw AssertionError
      Macros.assertEqual((1 to 10).sum, 55).must_==(())
    }
  }

}
