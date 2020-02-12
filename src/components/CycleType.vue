<template>
  <div>
    <h1 style="float: left">循环方式列表</h1>
    <el-form :inline="true" class="demo-form-inline" style="float: right">
      <el-link href="https://www.cnblogs.com/yanghj010/p/10875151.html">cron表达式帮助</el-link>
      <router-link to="/" active-class="router-active">
        <el-link>导航栏</el-link>
      </router-link>
      <el-button
        class="el-icon-refresh"
        type="text"
        @click="refreshData">刷新
      </el-button>
    </el-form>
    <br><br><br><br><br>
    <el-switch v-model="showIdSwitch" active-value="true" inactive-value="false"
               active-text="显示ID" inactive-text="隐藏ID"
               style="height:50px;float: left" @change="changeShowId">
    </el-switch>
    <el-button
      class="el-icon-circle-plus-outline"
      type="text"
      @click="dialogVisible = true">添加
    </el-button>
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
        label="描述">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="cron表达式">
        <template slot-scope="scope">
          <span>{{ scope.row.cronExpression }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="工作日过滤">
        <template slot-scope="scope">
          <span>{{ scope.row.workdayStatus | changeWorkdayStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="次数">
        <template slot-scope="scope">
          <span>{{ scope.row.times }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="开始时间">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime | formatDate1 }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="结束时间">
        <template slot-scope="scope">
          <span>{{ scope.row.endTime  | formatDate1 }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="删除"
        fixed="right"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)">
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-form :model="cycleTypeForm" :rules="rules" ref="cycleTypeForm" label-width="70px" class="demo-cycleTypeForm"
             size="medium">
      <el-dialog
        title="添加新循环方式"
        :append-to-body='true'
        :visible.sync="dialogVisible"
        width="60%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="cycleTypeForm.id"/>
        <el-form-item label="描述" prop="description">
          <el-input v-model="cycleTypeForm.description"/>
        </el-form-item>
        <el-form-item label="表达式" prop="cronExpression">
          <el-tooltip class="item" effect="light" content="Top Center 提示文字" placement="top">
            <el-input v-model="cycleTypeForm.cronExpression"/>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="工作日" prop="workdayStatus">
          <el-select v-model="cycleTypeForm.workdayStatus">
            <el-option
              v-for="(status, index) in workdayStatusList"
              :key="index"
              :value="status.value"
              :label="status.description"/>
          </el-select>
        </el-form-item>
        <el-form-item label="次数" prop="times">
          <el-input v-model="cycleTypeForm.times"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            type="datetime" placeholder="选择开始日期"
            v-model="cycleTypeForm.startTime"
            style="width: 100%;"/>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker
            type="datetime" placeholder="选择结束日期"
            v-model="cycleTypeForm.endTime"
            style="width: 100%;"/>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="addCycleType()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>

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
  // import {formatDate} from "../utils/formatDate.js";//路径要注意下
  export default {
    data() {
      return {
        cycleTypeForm: {
          id: 0,
          cronExpression: '',
          description: '',
          workdayStatus: 0,
          times: 0,
          startTime: '',
          endTime: '',
          createTime: '',
          updateTime: '',
        },
        rules: {
          description: [
            {required: true, message: '请输入该策略简短的描述', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          cronExpression: [
            {required: true, message: '请输cron表达式', trigger: 'blur'},
            {min: 0, message: '长度11 个字符', trigger: 'blur'}
          ],
        },
        workdayStatusList: [
          {
            description: '不设置',
            value: 0
          },
          {
            description: '跳过节假日',
            value: 1
          },
          {
            description: '跳过工作日',
            value: 2
          }
        ],
        tableData: [],
        search: '',
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
      handleDelete(index, row) {
        console.log(index, row);
        this.$confirm('删除操作, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios({
            method: 'delete',
            url: '/v1/cycleType/' + row.id
          }).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log(response);
            this.handleCurrentChange();
          }).catch(error => {
            console.log(error);
          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
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
          method: 'get',
          url: '/v1/cycleType',
          data: postData
        }).then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      cancel() {
        this.dialogVisible = false;
        this.emptyCycleTypeData();
      },
      emptyCycleTypeData() {
        this.cycleTypeForm = {
          id: '',
          description: '',
          cronExpression: '',
          workdayStatus: 0,
          startTime: '',
          endTime: '',
          times: 0,
        }
      },
      addCycleType() {
        let postData = JSON.stringify({
          description: this.cycleTypeForm.description,
          cronExpression: this.cycleTypeForm.cronExpression,
          workdayStatus: this.cycleTypeForm.workdayStatus,
          startTime: this.cycleTypeForm.startTime,
          endTime: this.cycleTypeForm.endTime,
          times: this.cycleTypeForm.times,
        });
        this.axios({
          method: 'post',
          url: '/v1/cycleType',
          data: postData
        }).then(response => {
          this.axios.get('/v1/cycleType').then(response => {
            this.tableData = response.data;
            this.currentPage = 1;
            this.$message({
              type: 'success',
              message: '已添加!'
            });
          }).catch(error => {
            console.log(error);
          });
          this.dialogVisible = false
          console.log(response);
        }).catch(error => {
          console.log(error);
        });
      },
      getPages() {
        this.axios.get('/v1/cycleType').then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      refreshData() {
        location.reload();
      },
    },

    filters: {
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
      changeWorkdayStatus(value) {
        if (value === '') {
          return ''
        }else if (value === 0){
          return '未设置'
        }else if (value === 1){
          return '跳过节假日'
        }else if (value === 2){
          return '跳过工作日'
        }
      }
    },

    created() {
      this.axios.get('/v1/cycleType').then(response => {
        this.tableData = response.data;
      }).catch(error => {
        console.log(error);
      });
    }
  }
</script>
<style scoped>
  .pages {
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
