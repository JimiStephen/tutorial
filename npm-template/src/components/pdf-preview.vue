<template>
  <div id="pdfpriviewDiv">
    <PDFViewer
      v-bind="{url}"
      @document-errored="onDocumentErrored"
    >
      <PDFUploader
        v-if="enableUploader"
        :documentError="documentError"
        @updated="urlUpdated"
        slot="header"
        class="header-item"
      />
    </PDFViewer>
  </div>
</template>

<script>
  import PDFUploader from '@/components/pdf/PDFUploader.vue'
  import PDFViewer from '@/components/pdf/PDFViewer.vue'

  export default {
    name: "pdf-preview",
    data() {
      return {
        dialogImageUrl: "",
        currentPage: 0,
        pageCount: 0,
        pdfUrl: "",
        url: "",
        documentError: undefined,
        enableUploader: false,
      };
    },
    components: {
      PDFUploader,
      PDFViewer,
    },
    methods: {
      urlUpdated(url) {
        this.documentError = undefined;
        this.url = url;
      },
      onDocumentErrored(e) {
        this.documentError = e.text;
      },
    },
    mounted() {
      let pdfurl = this.$route.query.pdfurl;
      let b = new Buffer(pdfurl, 'base64')
      this.url = b.toString();
    }
  }
</script>

<style>
  #pdfpriviewDiv, .pdf-viewer {
    margin: 0;
    padding: 0;
    background-color: #606f7b;
  }

  #pdfpriviewDiv {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #62637a;
  }

  label.form {
    color: white;
    font-family: Monaco, 'Courier New', Courier, monospace;
    font-weight: bold;
    margin-bottom: 2em;
    display: block;
  }

  input {
    padding: 0.45em;
    font-size: 1em;
  }

  .error {
    border: 1px solid red;
    background: pink;
    color: red;
    padding: 0.5em 3em;
    display: inline;
  }

  a.icon {
    cursor: pointer;
    display: block;
    border: 1px #333 solid;
    background: white;
    color: #333;
    font-weight: bold;
    padding: 0.25em;
    width: 1em;
    height: 1em;
    font-size: 1.5em;
  }

  .box-shadow {
    box-shadow: 0 15px 30px 0 rgba(0, 0, 0, 0.11), 0 5px 15px 0 rgba(0, 0, 0, 0.08);
  }

  .overflow-hidden {
    overflow: hidden;
  }

  @media print {
    body {
      background-color: transparent;
    }

    #pdfpriviewDiv {
      margin: 0;
      padding: 0;
    }
  }
</style>
