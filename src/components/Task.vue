<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input
          v-model="search"
          class="search_id"
          size="mini"
          placeholder="输入ID查询">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="onSearch()"
          class="el-icon-search">查询
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="getPages()"
          class="el-icon-search">获取全部任务
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
        <el-button
          class="el-icon-circle-plus-outline"
          type="text"
          @click="dialogVisible = true">添加
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        label="编号">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="任务内容">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="描述">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <span>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="分值">
        <template slot-scope="scope">
          <span>{{ scope.row.score }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="循环方式">
        <template slot-scope="scope">
          <span>{{ scope.row.cycleTypeId }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="更新日期">
        <template slot-scope="scope">
          <i class="el-icon-time hidden-sm-and-down"/>
          <span>{{ scope.row.updateTime | formatDate1 }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column-->
      <!--        label="备注">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-popover trigger="hover" placement="right">-->
      <!--            <p>所属用户: {{ scope.row.userName }}</p>-->
      <!--            <p>更新时间: {{ scope.row.updateTime }}</p>-->
      <!--            <p>创建时间：{{ scope.row.createTime }}</p>-->
      <!--            <div slot="reference" class="name-wrapper">-->
      <!--              <el-button type="text">{{ scope.row.id }}</el-button>-->
      <!--            </div>-->
      <!--          </el-popover>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column
        label="操作"
        fixed="right"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-form :model="taskForm" :rules="rules" ref="taskForm" label-width="70px" class="demo-taskForm" size="medium">
      <el-dialog
        title="添加新任务"
        :append-to-body='true'
        :visible.sync="dialogVisible"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="taskForm.id"/>
        <el-form-item label="内容" prop="content">
          <el-input v-model="taskForm.content"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="taskForm.description"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="taskForm.status"/>
        </el-form-item>
        <el-form-item label="分值" prop="score">
          <el-input v-model="taskForm.score"/>
        </el-form-item>
        <el-form-item label="循环方式" prop="cycleTypeId">
          <el-input v-model="taskForm.cycleTypeId"/>
        </el-form-item>

        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker type="datetime" placeholder="选择开始日期" v-model="taskForm.startTime"
                          style="width: 100%;"/>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker type="datetime" placeholder="选择结束日期" v-model="taskForm.endTime"
                          style="width: 100%;"/>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="addTask()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>

    <el-form :model="taskForm" :rules="rules" ref="taskForm" label-width="70px" class="demo-taskForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogUpdate"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="taskForm.id"/>
        <el-form-item label="内容" prop="content">
          <el-input v-model="taskForm.content"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="taskForm.description"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="taskForm.status"/>
        </el-form-item>
        <el-form-item label="分值" prop="score">
          <el-input v-model="taskForm.score"/>
        </el-form-item>
        <el-form-item label="循环方式" prop="cycleTypeId">
          <el-input v-model="taskForm.cycleTypeId"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="userDate">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="taskForm.startTime"
                          style="width: 100%;"/>
        </el-form-item>
        <el-form-item label="结束时间" prop="userDate">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="taskForm.endTime"
                          style="width: 100%;"/>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="updateTask()" type="primary" size="medium">确 定</el-button>
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
        taskForm: {
          id: '',
          content: '',
          description: '',
          cycleTypeId: '',
          status: '',
          score: '',
          updateTime: '',
          createTime: ''
        },
        rules: {
          userName: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur'}
          ],
          userAddress: [
            {required: true, message: '请输入住址', trigger: 'blur'},
            {min: 5, message: '长度大于 5 个字符', trigger: 'blur'}
          ],
        },
        tableData: [],
        search: '',
        dialogVisible: false,
        dialogUpdate: false,
        pageSize: 10,
        currentPage: 1,
        total: 20,
        disablePage: false
      }
    },
    methods: {
      handleEdit(index, row) {
        this.dialogUpdate = true;
        this.taskForm = Object.assign({}, row); //这句是关键！！！
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
            url: '/v1/task/' + row.id
          }).then(response => {
            this.axios.delete('/v1/task/' + row.id).then(response => {
              this.tableData = response.data;
            }).catch(error => {
              console.log(error);
            });
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log(response);
            location.reload();
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
          method: 'post',
          url: '/page',
          data: postData
        }).then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      cancel() {
        this.dialogUpdate = false;
        this.dialogVisible = false;
        this.emptyTaskData();
      },
      emptyTaskData() {
        this.taskForm = {
          id: '',
          content: '',
          description: '',
          cycleTypeId: '',
          status: '',
          score: '',
          updateTime: '',
          createTime: ''
        }
      },
      addTask() {
        let postData = JSON.stringify({
          content: this.taskForm.content,
          description: this.taskForm.description,
          cycleTypeId: this.taskForm.cycleTypeId,
          status: this.taskForm.status,
          score: this.taskForm.score
        });
        this.axios({
          method: 'post',
          url: '/v1/task',
          data: postData
        }).then(response => {
          this.axios.get('/v1/task').then(response => {
            this.tableData = response.data;
            this.currentPage = 1;
            this.$message({
              type: 'success',
              message: '已添加!'
            });
          }).catch(error => {
            console.log(error);
          });
          this.getPages();
          this.dialogVisible = false
          console.log(response);
          location.reload();
        }).catch(error => {
          console.log(error);
        });
      },
      updateTask() {
        let postData = this.qs.stringify({
          id: this.taskForm.id,
          content: this.taskForm.content,
          description: this.taskForm.description,
          cycleTypeId: this.taskForm.cycleTypeId,
          status: this.taskForm.status,
          score: this.taskForm.score
        });
        this.axios({
          method: 'put',
          url: '/v1/task',
          data: postData
        }).then(response => {
          this.handleCurrentChange();
          this.cancel();
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          console.log(response);
          location.reload();
        }).catch(error => {
          this.$message({
            type: 'success',
            message: '更新失败!'
          });
          console.log(error);
        });
      },
      onSearch() {
        let postData = this.qs.stringify({
          userName: this.search
        });
        this.axios({
          method: 'post',
          url: '/ListByName',
          data: postData
        }).then(response => {
          this.tableData = response.data;
          this.disablePage = true;
        }).catch(error => {
          console.log(error);
        });
      },
      getPages() {
        this.axios.get('/v1/task').then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      refreshData() {
        location.reload();
      },
      // created() {
      //   /*this.axios.get('static/user.json').then(response =>
      //   {
      //       this.tableData = response.data.tableData;
      //       this.total = response.data.tableData.length;
      //       // console.log(JSON.parse(JSON.stringify(response.data))['tableData'])
      //   });*/
      //   this.axios.post('/v1/task').then(response => {
      //     this.tableData = response.data;
      //   }).catch(error => {
      //     console.log(error);
      //   });
      //
      //   // this.axios.post('/rows').then(response => {
      //   //   this.total = response.data;
      //   // }).catch(error => {
      //   //   console.log(error);
      //   // });
      // },
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
      }
    },

    created() {
      /*this.axios.get('static/user.json').then(response =>
           {
               this.tableData = response.data.tableData;
               this.total = response.data.tableData.length;
               // console.log(JSON.parse(JSON.stringify(response.data))['tableData'])
           });*/
      this.axios.get('/v1/task').then(response =>
      {
        this.tableData = response.data;
      }).catch(error =>
      {
        console.log(error);
      });

      // this.axios.post('/rows').then(response =>
      // {
      //   this.total = response.data;
      // }).catch(error =>
      // {
      //   console.log(error);
      // });
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
