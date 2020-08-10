<template lang="html">
  <div class="editor">
    <div ref="toolbar" class="toolbar"></div>
    <div ref="editor" class="text"></div>
  </div>
</template>
<style lang="css" scoped>
  .editor {
    width: 100%;
    margin: 0 auto;
  }

  .toolbar {
    border: 1px solid #ccc;
  }

  .text {
    border: 1px solid #ccc;
    height: 210px;
  }
</style>

<script>
  import E from 'wangeditor'

  export default {
    name: 'EditorBar',
    data() {
      return {
        editor: null,
        content: ''
      }
    },
    model: {
      prop: 'value',
      event: 'change'
    },
    props: ['imgConfig', 'value', 'isClear'],
    watch: {
      value(val) {
        // 使用 v-model 时，同步内容
        if (!val) {
          this.editor.txt.clear();
        } else {
          let bool = sessionStorage.editorInitVal == 'true' ? true : false;
          if (bool) {
            this.editor.txt.html(val);
            // 修改功能填充值时请及时清除缓存，避免每次都刷新编辑器内容导致光标消失。
            sessionStorage.editorInitVal = !bool;
          }
        }
      },
      isClear(val) {
        if (val && typeof (val) == 'boolean' && val == true) {
          // 触发清除文本域内容
          this.editor.txt.clear()
          this.content = ''
        }
      },
    },
    mounted() {
      this.initEditor();
    },
    methods: {
      initEditor() {
        this.editor = new E(this.$refs.toolbar, this.$refs.editor)

        //this.editor.customConfig.uploadImgShowBase64 = true // base 64 存储图片
        this.editor.customConfig.uploadImgServer = this.imgConfig.action;// 配置服务器端地址
        this.editor.customConfig.uploadImgHeaders = {}// 自定义 header
        this.editor.customConfig.uploadFileName = this.imgConfig.name // 后端接受上传文件的参数名
        this.editor.customConfig.uploadImgMaxSize = this.imgConfig.size * 1024// 将图片大小限制为 2M
        this.editor.customConfig.uploadImgMaxLength = 20 // 限制一次最多上传 3 张图片
        this.editor.customConfig.uploadImgTimeout = 3 * 60 * 1000 // 设置超时时间
        this.editor.customConfig.pasteFilterStyle = false
        // 配置菜单
        this.editor.customConfig.menus = [
          'head', // 标题
          'bold', // 粗体
          'fontSize', // 字号
          'fontName', // 字体
          'italic', // 斜体
          'underline', // 下划线
          'strikeThrough', // 删除线
          'foreColor', // 文字颜色
          'backColor', // 背景颜色
          'link', // 插入链接
          'list', // 列表
          'justify', // 对齐方式
          'quote', // 引用
          'emoticon', // 表情
          'image', // 插入图片
          'table', // 表格
        ]

        this.editor.customConfig.uploadImgHooks = {
          fail: (xhr, editor, result) => {
            // 插入图片失败回调
          },
          success: (xhr, editor, result) => {
            // 图片上传成功回调
            let imgItem = "";
            let attachUrlList = result.re.data.attachUrlList;
            for (let url of attachUrlList) {
              imgItem = imgItem + '<img src="' + (this.imgConfig.url + url) + '" style="max-width:100%;">'
            }

            this.editor.cmd.do('insertHTML', imgItem)
            //该方法效率比较低
            //this.editor.uploadImg.insertLinkImg(imgUrl)
          },
          timeout: (xhr, editor) => {
            // 网络超时的回调
          },
          error: (xhr, editor) => {
            // 图片上传错误的回调
          },
          customInsert: (insertImg, result, editor) => {
            // 图片上传成功，插入图片的回调
          }
        }
        this.editor.customConfig.onchange = (html) => {
          this.content = html // 绑定当前逐渐地值
          this.$emit('change', this.content) // 将内容同步到父组件中
        }

        // 创建富文本编辑器
        this.editor.create()
        // 初始化赋值，防止第一次赋值失败
        this.editor.txt.html(this.value)
      }
    }
  }
</script>
