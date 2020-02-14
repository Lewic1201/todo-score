<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-date-picker type="datetime" placeholder="选择日期" v-model="searchDay"
                        style="width: 100%;"/>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="getRecordByDay()"
          class="el-icon-search">按日期查询
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="getPages()"
          class="el-icon-search">查询全部
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="el-icon-refresh"
          type="text"
          @click="refreshData">刷新
        </el-button>
      </el-form-item>
      <el-form-item>
        <router-link to="/" active-class="router-active">
          导航栏
        </router-link>
      </el-form-item>
    </el-form>
    <br>
    <el-switch v-model="showIdSwitch" active-value="true" inactive-value="false"
               active-text="显示ID" inactive-text="隐藏ID"
               style="height:50px;float: left" @change="changeShowId">
    </el-switch>
    <el-table
      :data="tableData"
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        label="编号"
        v-if="showId">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="任务内容">
        <template slot-scope="scope">
          <span>{{ scope.row.task.content }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="分值">
        <template slot-scope="scope">
          <span>{{ scope.row.task.score}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="得分">
        <template slot-scope="scope">
          <span>{{ scope.row.score}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="是否完成">
        <template slot-scope="scope">
          <span>{{ scope.row.finish | isFinish }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="更新日期">
        <template slot-scope="scope">
          <i class="el-icon-time hidden-sm-and-down"/>
          <span>{{ scope.row.updateTime | formatDate1 }}</span>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <div class="pages">
      <el-pagination
        background
        :disabled="disablePage"
        :current-page.sync="currentPage"
        small
        layout="prev, pager, next"
        :page-size="pageSize"
        :total="total"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        taskRecordForm: {
          id: '',
          task: {
            id: '',
            content: '',
            score: '',
            status: '',
            description: '',
            cycleTypeId: ''
          },
          finish: '',
          score: ''
        },
        searchDay: '',
        tableData: [],
        dialogVisible: false,
        dialogUpdate: false,
        pageSize: 10,
        currentPage: 1,
        total: 0,
        disablePage: false,
        showIdSwitch: false,
        showId: false,
      }
    },
    methods: {
      changeShowId(data) {
        this.showId = data === "true";
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      handleCurrentChange() {
        console.log(`当前页: ${this.currentPage}`);
        let postData = this.qs.stringify({
          page: this.currentPage
        });
        this.axios({
          method: 'post',
          url: '/page',
          data: postData
        }).then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      getRecordByDay() {
        this.axios({
          method: 'get',
          url: '/v1/record/task/day/' + this.searchDay
        }).then(response => {
          this.tableData = response.data;
          this.disablePage = true;
        }).catch(error => {
          console.log(error);
        });
      },
      getPages() {
        this.axios.get('/v1/record/task/history').then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      refreshData() {
        location.reload();
      }
    },

    filters: {
      // import {formatDate} from "../utils/formatDate.js";//路径要注意下
      formatDate1: function (value) {// 时间戳转换日期格式方法
        if (value == null) {
          return '';
        } else {
          let date = new Date(value);
          let y = date.getFullYear();// 年
          let MM = date.getMonth() + 1;// 月
          MM = MM < 10 ? ('0' + MM) : MM;
          let d = date.getDate();// 日
          d = d < 10 ? ('0' + d) : d;
          let h = date.getHours();// 时
          h = h < 10 ? ('0' + h) : h;
          let m = date.getMinutes();// 分
          m = m < 10 ? ('0' + m) : m;
          let s = date.getSeconds();// 秒
          s = s < 10 ? ('0' + s) : s;
          return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
        }
      },

      isFinish: function (value) {
        if (value == null) {
          return '';
        } else if (value === true) {
          return '是';
        } else if (value === false) {
          return '否';
        }

      }
    },

    created() {
      this.axios.get('/v1/record/task/history').then(response => {
        this.tableData = response.data.data;
        this.currentPage = response.data.pageNum;
        this.total = response.data.total;
      }).catch(error => {
        console.log(error);
      });
    }
  }
</script>
<style scoped>
  .search_name {
    width: 200px;
  }

  .pages {
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
