SUMMARY = "Test programs for IMX53 BSP"
DESCRIPTION = "Unit tests for the IMX53 BSP"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r2"

COMPATIBLE_MACHINE = "(imx53ard|imx53qsb)"

SRC_URI = "file://imx-test-11.09.01.tar.gz \
           file://0001-ENGR00158471-fix-ipu-unit-test-application-missing-i.patch \
           file://0002-ENGR00170223-vpu-Fix-encoder-with-rotation-90-or-270.patch \
           file://0003-ENGR00162747-fix-asrc-sample-rate-convert-issue.patch \
           file://selective-dirs-only.patch"

S = "${WORKDIR}/${PN}-11.09.01"

# override parallel make flags
#PARALLEL_MAKE="-j 1"
#EXTRA_OEMAKE = ""

do_compile () {
#    INCLUDE_DIR="-I${STAGING_INCDIR} -I${STAGING_KERNEL_DIR}/drivers/mxc/security/rng/include \
#                 -I${STAGING_KERNEL_DIR}/drivers/mxc/security/sahara2/include"
    oe_runmake CROSS_COMPILE="${HOST_PREFIX}" PLATFORM="IMX53" KBUILD_OUTPUT="${STAGING_KERNEL_DIR}" LINUXPATH="${STAGING_KERNEL_DIR}" all
}

#for iMX53:
FILES_${PN} = "/platform/IMX53/*"

do_install () {
    oe_runmake DESTDIR="${D}" install
}



