SUMMARY ="openZwave recipe for yocto"

DESCRIPTION = "openzwave allow to control device that use zwave protocole for home automation ..."

AUTHOR = "modjo.buggy22@orange.fr"
 
DEPENDS = "\
    systemd \
"

S = "${WORKDIR}/git"

inherit pkgconfig

LICENSE = "Apache-2.0 & GPLv2 & LGPLv2+ "

LIC_FILES_CHKSUM = " \
    file://license/Apache-License-2.0.txt;md5=5dbc052533cb6e0e47352828d40f42f2 \
    file://license/gpl.txt;md5=1c775619c98e9b11e58da29617fc9c9f \
    file://license/lgpl.txt;md5=7be289db0a5cd2c8acf72a8cbd0c15df \
"

GIT_URL = "git://github.com/OpenZWave/open-zwave.git"
SRC_URI = "${GIT_URL};branch=master;protocol=git"

SRCREV = "67f32fbdc018544a07635b5c8ed202c7b1de62c9"


do_configure[noexec] = "1"

do_compile() {
    export PREFIX="${D}${prefix}"
    export pkgconfigdir="${PREFIX}${base_libdir}/pkgconfig"
    export instlibdir="${PREFIX}${base_libdir}"
    make BITBAKE_ENV=1
}

do_install () {
    export PREFIX="${D}${prefix}"
    export pkgconfigdir="${PREFIX}${base_libdir}/pkgconfig"
    export instlibdir="${PREFIX}${base_libdir}"
    make BITBAKE_ENV=1 install
    sed "s|${D}||" -i ${pkgconfigdir}/libopenzwave.pc
}

FILES_${PN} += "${prefix}${sysconfdir}/${PN}/*"
FILES_${PN}-dev += "${prefix}${base_libdir}/pkgconfig/*.pc"
