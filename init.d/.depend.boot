TARGETS = console-setup mountkernfs.sh resolvconf ufw hostname.sh plymouth-log apparmor screen-cleanup udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh mountdevsubfs.sh checkroot.sh urandom networking open-iscsi iscsid lvm2 checkfs.sh mountall.sh mountall-bootclean.sh bootmisc.sh procps checkroot-bootclean.sh mountnfs.sh kmod mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
urandom: hwclock.sh
networking: mountkernfs.sh urandom resolvconf procps
open-iscsi: networking iscsid
iscsid: networking
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh mountnfs-bootclean.sh udev
procps: mountkernfs.sh udev
checkroot-bootclean.sh: checkroot.sh
mountnfs.sh: networking
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
