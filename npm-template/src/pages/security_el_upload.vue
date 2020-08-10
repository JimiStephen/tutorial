<template>
  <div>
    <!-- 预览显示 -->
    <div
      class="images"
      v-viewer.static="{className: 'v-viewer-witout-ppn', url: 'data-large',  'inline': false, 'button': true, 'navbar': false, 'title': false, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true}"
    >
      <img width="100%" :data-large="dialogImageUrl" alt>
    </div>
    <el-upload
      :limit="limit"
      :action="fileUploadUrl"
      list-type="picture-card"
      :file-list="init_image_file_list"
      :on-success="onsuccess"
      :on-preview="onpreview"
      :on-remove="onremove"
      :on-exceed="onexceed"
      :before-upload="beforeupload"
      ref="elUploadFile"
      :disabled="disabled"
      :with-credentials="true"
      :accept="fileAccept"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <div v-show="showMaxLimitMessage" class="el-form-item__error">最多允许上传{{this.$props.limit}}张图片</div>


  </div>
</template>

<script>
  import {getImageUrlWithInternetMode, getImageUrlWithRPCMode, uploadImageRPCUrl} from '@/service/api'
  import {getDynamicUrl} from "@/utils.js";


  export default {
    props: {
      limit: {
        type: Number,
        default: 1
      },
      disabled: {
        type: Boolean,
        default: false
      },
      enableDoDefaultonSuccess: {
        type: Boolean,
        default: true
      },
      enableDoDefaultPreview: {
        type: Boolean,
        default: true
      },
      enableDoDefaultonRemove: {
        type: Boolean,
        default: true
      },
      fileAccept: {
        type: String,
        default: ".jpg, .jpeg, .png"
      },
      on_success: {
        type: Function
      },
      on_preview: {
        type: Function
      },
      on_remove: {
        type: Function
      },
      before_upload: {
        type: Function
      }
    },
    data() {
      return {
        showMaxLimitMessage: false,
        image_file_list: [],
        init_image_file_list: [],
        fileUploadUrl: getDynamicUrl('') + "/fcarcmt/api/upload/file.do_",
        securityFleUploadUrl: getDynamicUrl('') + uploadImageRPCUrl,
        dialogVisible: false,
        dialogImageUrl: '',
        uploadUrlInfo: {
          name: "",
          path: "",
          full_filename: ""
        },
      }
    },
    methods: {
      resetErrorMessage() {
        this.showMaxLimitMessage = false;
      },
      setImageData(data) {
        if (null != data && data.length > 0) {
          this.image_file_list = []
          this.init_image_file_list = []
          for (var i = 0; i < data.length; i++) {
            var pictureObj = {}
            pictureObj.name = data[i].name
            if (!this.checkURL(data[i].url)) {
              pictureObj.full_name = data[i].url
              pictureObj.url = getImageUrlWithRPCMode(pictureObj.full_name)
            } else {
              pictureObj.full_name = data[i].url
              pictureObj.url = getImageUrlWithInternetMode(data[i].url)
            }
            this.image_file_list.push(pictureObj)
            this.init_image_file_list.push(pictureObj)
          }
        }
      },
      setImageSimpleData(data) {
        this.image_file_list = []
        this.init_image_file_list = []
        if (null != data && data.length > 0) {
          for (var i = 0; i < data.length; i++) {
            let url = data[i];
            var pictureObj = {}
            pictureObj.name = url;
            if (!this.checkURL(url)) {
              pictureObj.full_name = url;
              pictureObj.url = getImageUrlWithRPCMode(pictureObj.full_name)
            } else {
              pictureObj.full_name = url;
              pictureObj.url = getImageUrlWithInternetMode(url)
            }
            this.image_file_list.push(pictureObj)
            this.init_image_file_list.push(pictureObj)
          }
        }
      },
      getImageData() {
        var result = []
        var data = this.image_file_list
        for (var i = 0; i < data.length; i++) {
          var pictureObj = {}
          pictureObj.name = data[i].name
          pictureObj.url = data[i].full_name
          result.push(pictureObj)
        }
        return result
      },
      getImageSimpleData() {
        var result = []
        var data = this.image_file_list
        for (var i = 0; i < data.length; i++) {
          result.push(data[i].full_name)
        }
        return result
      },
      clearImage() {
        this.$refs.elUploadFile.clearFiles();
      },
      checkURL(str) {
        if (str.match('^(http|https)://') == null) {
          return false
        } else {
          return true;
        }
      },
      onsuccess(response, file, fileList) {
        if (this.enableDoDefaultonSuccess) {

          // 修复存在，返回值为null的情况，代表上传失败，清理掉刚上传的文件
          if (!response || !response.re) {
            let idx = fileList.indexOf(file);
            idx != -1 && (fileList.splice(idx, 1));
            return;
          }

          this.uploadUrlInfo.name = file.name
          this.uploadUrlInfo.path = response.re.names.full_path

          var pictureObj = {}
          // 如果是使用了相对路径文件名的情况（path = "V2/201810/1/3-74cdefe28dd24663bcc01c3177f63bc7-r-o-0.jpg"）
          if (!this.checkURL(this.uploadUrlInfo.path)) {
            pictureObj.full_name = this.uploadUrlInfo.path
            this.uploadUrlInfo.path = getImageUrlWithRPCMode(this.uploadUrlInfo.path)
          } else {
            pictureObj.full_name = this.uploadUrlInfo.path
            this.uploadUrlInfo.path = getImageUrlWithInternetMode(this.uploadUrlInfo.path)
          }
          pictureObj.name = this.uploadUrlInfo.name;
          pictureObj.url = this.uploadUrlInfo.path;

          this.image_file_list.push(pictureObj)
        }
        if (null != this.on_success) {
          this.on_success(response, file, fileList)
        }
        // console.log("添加：" + JSON.stringify(file));
        // console.log("添加图片后：" + JSON.stringify(this.image_file_list));
      },
      onpreview(file) {
        if (this.enableDoDefaultPreview) {
          this.dialogImageUrl = file.url;
          this.dialogVisible = true;
          let isPdf = false;
          if (file.raw) {
            let fileType = file.raw.type;
            isPdf = fileType.endsWith("pdf")
          } else {
            isPdf = this.dialogImageUrl.endsWith("pdf");
          }


          if (isPdf) {

            var b = new Buffer(this.dialogImageUrl);
            var s = b.toString('base64');
            var winopen = this.callNewPage('/common/pdfpreview', {pdfurl: s});
          } else {
            const viewer = this.$el.querySelector('.images').$viewer;
            viewer.show();
          }
        }

        if (null != this.on_preview) {
          this.on_preview(file)
        }
      },
      onexceed(file, fileList) {
        this.showMaxLimitMessage = true;
      },
      onremove(file, fileList) {
        if (this.enableDoDefaultonRemove) {
          let removeitem = file.full_name;
          if (!!!removeitem && file.response) {
            removeitem = file.response.re.names.full_path;
          }

          for (var i = 0; i < this.image_file_list.length; i++) {
            if (this.image_file_list[i].full_name === removeitem) {
              this.image_file_list.splice(i, 1);
              break;
            }
          }
        }
        if (null != this.on_remove) {
          this.on_remove(file, fileList)
        }

        // console.log("删除：" + JSON.stringify(file));
        // console.log("删除图片后：" + JSON.stringify(this.image_file_list));
      },
      callNewPage(url, options = {}) {
        let path = /\/$/.test(url) ? url : `${url}/`
        const target = /^(http|https):\/\//.test(path) ? url
          : `/#${url}`
        let queryStr = ''
        for (let key in options) {
          queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
        }
        return window.open(`${target}${queryStr}`);
      },
      beforeupload(file) {
        if (null != this.before_upload) {
          return this.before_upload(file);
        }
        const isLt5M = file.size / 1024 / 1024 < 10;
        if (!isLt5M) {
          this.$message.error('上传头像图片大小不能超过 10MB!');
          return false;
        }
        const isMarch = file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/jpeg' || file.type === 'application/pdf';

        if (!isMarch) {
          this.$message.error('上传图片只能是PNG, JPG, JPEG 格式!')
        }

        return isMarch;
      }
    },
    mounted() {
    }
  }
</script>
